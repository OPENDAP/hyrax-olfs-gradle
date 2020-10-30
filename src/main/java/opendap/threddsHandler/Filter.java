/*
 * /////////////////////////////////////////////////////////////////////////////
 * // This file is part of the "Hyrax Data Server" project.
 * //
 * //
 * // Copyright (c) 2015 OPeNDAP, Inc.
 * // Author: Nathan David Potter  <ndp@opendap.org>
 * //
 * // This library is free software; you can redistribute it and/or
 * // modify it under the terms of the GNU Lesser General Public
 * // License as published by the Free Software Foundation; either
 * // version 2.1 of the License, or (at your option) any later version.
 * //
 * // This library is distributed in the hope that it will be useful,
 * // but WITHOUT ANY WARRANTY; without even the implied warranty of
 * // MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * // Lesser General Public License for more details.
 * //
 * // You should have received a copy of the GNU Lesser General Public
 * // License along with this library; if not, write to the Free Software
 * // Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301
 * //
 * // You can contact OPeNDAP, Inc. at PO Box 112, Saunderstown, RI. 02874-0112.
 * /////////////////////////////////////////////////////////////////////////////
 */
package opendap.threddsHandler;

import opendap.namespaces.THREDDS;
import org.jdom.Element;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ndp on 4/17/15.
 */
public class Filter {


    String includeAllRegexString = ".*$";

    Vector<Clude> _cludes;


    public Filter(Element filter) {
        _cludes = new Vector<>();
        if (filter != null) {
            for (Object o : filter.getChildren()) {

                Element cludeElement = (Element) o;

                Clude clude = new Clude(cludeElement);

                _cludes.add(clude);

            }
        }
        else {
            _cludes.add(new Clude());
        }

    }



    public class Clude {
        String regex;
        String wildcard;
        String atomicAttrVal;
        String collectionAttrVal;
        boolean appliesToAtomic;
        boolean appliesToCollection;
        boolean excludeMatching;

        Pattern wildCardPattern;
        Pattern regexPattern;


        /**
         * Default includes everything!
         */
        public Clude() {
            appliesToAtomic = true;
            appliesToCollection = false;
            excludeMatching = false;
            regexPattern = Pattern.compile(includeAllRegexString);
            wildCardPattern = Pattern.compile(includeAllRegexString);

        }

        public Clude(Element clude){
            this();

            if(clude==null)
                return;

            excludeMatching = clude.getName().equals(THREDDS.EXCLUDE);


            wildcard = clude.getAttributeValue(THREDDS.WILDCARD);
            if(wildcard!=null){
                String regex = makeRegexFromWildCard(wildcard);
                wildCardPattern = Pattern.compile(regex);
            }


            regex = clude.getAttributeValue(THREDDS.REGEXP);
            if(regex!=null){
                regexPattern = Pattern.compile(regex);
            }

            atomicAttrVal  = clude.getAttributeValue(THREDDS.ATOMIC);
            if(atomicAttrVal!=null)
                appliesToAtomic = Boolean.parseBoolean(atomicAttrVal);

            collectionAttrVal = clude.getAttributeValue(THREDDS.COLLECTION);
            if(collectionAttrVal!=null)
                appliesToCollection = Boolean.parseBoolean(collectionAttrVal);

        }


        boolean include(String s, boolean isNode){


            if( (!isNode && appliesToAtomic) || (isNode && appliesToCollection)){

                if(wildCardPattern.matcher(s).matches() && regexPattern.matcher(s).matches()) {
                    return !excludeMatching;
                }
                else {
                    return excludeMatching;
                }
            }

            return true;


        }

    }

    String makeRegexFromWildCard(String subject) {

        Pattern regex = Pattern.compile("[^*]+|(\\*)");
        Matcher m = regex.matcher(subject);
        StringBuffer b = new StringBuffer();
        while (m.find()) {
            if (m.group(1) != null) m.appendReplacement(b, ".*");
            else m.appendReplacement(b, "\\\\Q" + m.group(0) + "\\\\E");
        }
        m.appendTail(b);

        return b.toString();


    }



    boolean include(String name, boolean isNode){


        boolean include = false;
        for(Clude clude : _cludes){

            if(clude.include(name,isNode))
                include = true;
        }
        return include;

    }

}
