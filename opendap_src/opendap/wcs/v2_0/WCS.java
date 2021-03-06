/*
 * /////////////////////////////////////////////////////////////////////////////
 * // This file is part of the "Hyrax Data Server" project.
 * //
 * //
 * // Copyright (c) 2017 OPeNDAP, Inc.
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
package opendap.wcs.v2_0;

import org.jdom.Element;
import org.jdom.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * User: ndp
 * Date: Aug 14, 2008
 * Time: 10:45:26 AM
 */

public class WCS {


    private static final Logger log = org.slf4j.LoggerFactory.getLogger(WCS.class);


    public static final int       MAX_REQUEST_LENGTH = 65536;
    

    public static final String    WCS_NAMESPACE_STRING = "http://www.opengis.net/wcs/2.0";
    public static final Namespace WCS_NS = Namespace.getNamespace("wcs",WCS_NAMESPACE_STRING);
    public static final String    WCS_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/wcs/2.0/";

    public static final String    CURRENT_VERSION = "2.0.1";
    public static final String    SERVICE = "WCS";




    public static final String    XSI_NAMESPACE_STRING = "http://www.w3.org/2001/XMLSchema-instance";
    public static final Namespace XSI_NS = Namespace.getNamespace("xsi",XSI_NAMESPACE_STRING);



    public static final String    OWS_NAMESPACE_STRING = "http://www.opengis.net/ows/2.0";
    public static final Namespace OWS_NS = Namespace.getNamespace("ows",OWS_NAMESPACE_STRING);
    public static final String    OWS_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/ows/2.0/";


    public static final String    GMLCOV_NAMESPACE_STRING = "http://www.opengis.net/gmlcov/1.0";
    public static final Namespace GMLCOV_NS = Namespace.getNamespace("gmlcov",GMLCOV_NAMESPACE_STRING);
    public static final String    GMLCOV_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/gmlcov/1.0/";

    public static final String    GML_NAMESPACE_STRING = "http://www.opengis.net/gml/3.2";
    public static final Namespace GML_NS = Namespace.getNamespace("gml",GML_NAMESPACE_STRING);
    public static final String    GML_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/gml/3.2.1/";

    public static final String    SWE_NAMESPACE_STRING = "http://www.opengis.net/swe/2.0";
    public static final Namespace SWE_NS = Namespace.getNamespace("swe",SWE_NAMESPACE_STRING);
    public static final String    SWE_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/sweCommon/2.0/";


    public static final String    WCSEO_NAMESPACE_STRING = "http://www.opengis.net/wcs/wcseo/1.0";
    public static final Namespace WCSEO_NS = Namespace.getNamespace("wcseo",WCSEO_NAMESPACE_STRING);
    public static final String    WCSEO_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/wcseo/1.0/";

    public static final String    EOP_NAMESPACE_STRING = "http://www.opengis.net/eop/2.0";
    public static final Namespace EOP_NS = Namespace.getNamespace("wcs",EOP_NAMESPACE_STRING);
    public static final String    EOP_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/eop/2.0/";

    public static final String    OM_NAMESPACE_STRING = "http://www.opengis.net/om/2.0";
    public static final Namespace OM_NS = Namespace.getNamespace("wcs",OM_NAMESPACE_STRING);
    public static final String    OM_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/om/2.0/";

    public static final String    CIS_NAMESPACE_STRING = "http://www.opengis.net/cis/1.1";
    public static final Namespace CIS_NS = Namespace.getNamespace("cis",CIS_NAMESPACE_STRING);
    //public static final String    CIS_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/om/2.0/";


    public static final String    XLINK_NAMESPACE_STRING = "http://www.w3.org/1999/xlink";
    public static final Namespace XLINK_NS = Namespace.getNamespace("xlink",XLINK_NAMESPACE_STRING);
    public static final String    XLINK_SCHEMA_LOCATION_BASE= "http://schemas.opengis.net/xlink/1.0.0/";


    public enum REQUEST {
        GET_CAPABILITIES("GetCapabilities"),
        DESCRIBE_COVERAGE("DescribeCoverage"),
        GET_COVERAGE("GetCoverage"),
        DESCRIBE_EO_COVERAGE_SET("DescribeEOCoverageSet");

        private final String name;

        REQUEST(String name) {
            this.name = name;
        }

        @Override
        public String toString(){
            return name;
        }
    }



             /*

    public static String COVERAGE_OFFERING = "CoverageOffering";
    public static String COVERAGE_OFFERING_BRIEF = "CoverageOfferingBrief";
    public static String CONTENT_METADATA = "ContentMetadata";
    public static String NAME = "name";
    public static String LABEL = "label";
    public static String LON_LAT_ENVELOPE = "lonLatEnvelope";
    public static String DOMAIN_SET = "domainSet";
    public static String TEMPORAL_DOMAIN = "temporalDomain";
    public static String SPATIAL_DOMAIN = "spatialDomain";
    public static String SRS_NAME = "srsName";

    public static String SUPPORTED_CRSS = "supportedCRSs";
    public static String REQUEST_CRSS = "requestCRSs";
    public static String RESPONSE_CRSS = "responseCRSs";
    public static String NATIVE_CRSS = "nativeCRSs";
    public static String REQUEST_RESPONSE_CRSS = "requestResponseCRSs";

        */

    public static void checkNamespace(Element e, String name, Namespace namespace) throws WcsException {

        if(e==null){
            throw new WcsException("Missing element: "+name+" from namespace: "+namespace,
                    WcsException.MISSING_PARAMETER_VALUE,
                    namespace+name);
        }


        if(!e.getName().equals(name) || !e.getNamespace().equals(namespace)){
            String msg = "Internal dispatch operations " +
                    "have failed. The element '" + e.getNamespacePrefix() + (e.getNamespacePrefix().equals("")?"":":") +
                    e.getName() + "' " +
                    "has been incorrectly identified as element: "+name+" in namespace: "+namespace;
            log.error(msg);
            throw new WcsException(msg, WcsException.NO_APPLICABLE_CODE);
        }
    }




    public static void checkVersion(String ver) throws WcsException {

        if(ver==null){
            throw new WcsException("Missing WCS version!",
                    WcsException.MISSING_PARAMETER_VALUE,"version");
        }
        if(!CURRENT_VERSION.equals(ver)){
            throw new WcsException("Unsupported WCS " +
                    "version(s): "+ver,
                    WcsException.VERSION_NEGOTIATION_FAILED,"version");
        }
    }

    public static void checkService(String srvc) throws WcsException {

        if(srvc==null){
            throw new WcsException("Missing service type!",
                    WcsException.MISSING_PARAMETER_VALUE,"service");
        }
        if(!SERVICE.equalsIgnoreCase(srvc)){
            throw new WcsException("Unsupported service: " + srvc,
                    WcsException.VERSION_NEGOTIATION_FAILED,"service");
        }
    }


               /*


    public static void checkCoverageDescription(Element cd) throws WcsException{

        checkNamespace(cd,"CoverageDescription",WCS_NS);


        checkBoundedBy(cd.getChild("boundedBy", GML_NS));

        checkCoverageId(cd.getChild("CoverageId", WCS_NS));

        checkDomainSet(cd.getChild("domainSet", WCS_NS));
        checkRangeType(cd.getChild("rangeType", GMLCOV_NS));

        checkServiceParameters(cd.getChild("ServiceParameters", WCS_NS));


    }
    */

               /*
    public static void checkBoundedBy(Element e) throws WcsException{
        checkNamespace(e,"boundedBy",GML_NS);
    }

    public static void checkCoverageId(Element e) throws WcsException{
        checkNamespace(e,"CoverageId",WCS_NS);

        if(e.getTextTrim().equals(""))
            throw new WcsException("wcs:CoverageId MUST have a non empty value.",
                    WcsException.MISSING_PARAMETER_VALUE,"wcs:CoverageId");

    }



    public static void checkServiceParameters(Element e) throws WcsException{
        checkNamespace(e,"ServiceParameters",WCS_NS);




    }

        */

        /*

    public static void checkDomainSet(Element e) throws WcsException{

        checkNamespace(e,"domainSet",WCS.WCS_NS);
        checkSpatialDomain(e.getChild("SpatialDomain",WCS.WCS_NS));
        checkTemporalDomain(e.getChild("TemporalDomain",WCS.WCS_NS));

    }
    */
        /*
    public static void checkSpatialDomain(Element e) throws WcsException{

        checkNamespace(e,"SpatialDomain",WCS.WCS_NS);

        new NewBoundingBox(e.getChild("BoundingBox",WCS.OWS_NS));



    }
    */
        /*


    public static void checkTemporalDomain(Element e) throws WcsException{

        checkNamespace(e,"TemporalDomain",WCS.WCS_NS);
        boolean hasContentTP = false;
        boolean hasContenttP = false;
        boolean hasContent = false;
        Iterator tpositions = e.getChildren("timePosition",WCS.GML_NS).iterator();
        Iterator tperiods = e.getChildren("TimePeriod",WCS.WCS_NS).iterator();

        while(tpositions.hasNext()){
            hasContent = true;
            checkTimePosition((Element)tpositions.next());
        }

        while(tperiods.hasNext()){
            hasContent = true;
            checkTimePeriod((Element)tperiods.next());
        }
        if(!hasContent){
            throw new WcsException("wcs:TemporalDomain MUST have one or more gml:timePosition elements " +
                    "and/or one or more wcs:TimePeriod elements",
                    WcsException.MISSING_PARAMETER_VALUE,"wcs:TemporalDomain,gml:timePeriod");
        }
    }


    public static void checkTimePosition(Element e) throws WcsException{
        checkNamespace(e,"timePosition",WCS.GML_NS);
        //TODO Check the content of gml:timePosition


    }

    public static void checkTimePeriod(Element e) throws WcsException{
        checkNamespace(e,"TimePeriod",WCS.WCS_NS);

        Element begin = e.getChild("BeginPosition",WCS.WCS_NS);
        Element end   = e.getChild("EndPosition",WCS.WCS_NS);

        if(begin==null)
            throw new WcsException("wcs:TimePeriod MUST have a wcs:BeginPosition element ",
                    WcsException.MISSING_PARAMETER_VALUE,"wcs:BeginPosition");


        if(end==null)
            throw new WcsException("wcs:TimePeriod MUST have a wcs:EndPosition element ",
                    WcsException.MISSING_PARAMETER_VALUE,"wcs:EndPosition");

        //TODO Check the content of wcs:BeginPosition and wcs:EndPosition
    }


  */


        /*



    public static void checkRangeType(Element e) throws WcsException{
        checkNamespace(e,"Range",WCS.WCS_NS);

        Iterator fields = e.getChildren("Field",WCS.WCS_NS).iterator();

        if(!fields.hasNext())
            throw new WcsException("wcs:Range MUST have at least one wcs:Field element ",
                    WcsException.MISSING_PARAMETER_VALUE,"wcs:Field");

        Element field;
        while(fields.hasNext()){
            field = (Element)fields.next();
            checkField(field);

        }



    }

    public static void checkField(Element e) throws WcsException{
        checkNamespace(e,"Field",WCS.WCS_NS);

        checkCoverageId(e.getChild("Identifier", WCS.WCS_NS));
        checkDefinition(e.getChild("Definition",WCS.WCS_NS));
        checkInterpolationMethods(e.getChild("InterpolationMethods",WCS.WCS_NS));

    }

    public static void checkDefinition(Element e) throws WcsException{

        String errMsg = "ows:Definition MUST contain one of: ows:AllowedValues, ows:AnyValue, " +
                        "ows:NoValues, or ows:ValuesReference";
        Element pt;
        boolean hasCorrectContent = false;
        checkNamespace(e,"Definition",WCS.WCS_NS);


        pt = e.getChild("AllowedValues",WCS.OWS_NS);
        if(pt!=null){
            checkAllowedValues(pt);
            hasCorrectContent = true;
        }


        pt = e.getChild("AnyValue",WCS.OWS_NS);
        if(pt!=null){

            if(hasCorrectContent)
                throw new WcsException(errMsg, WcsException.INVALID_PARAMETER_VALUE,"wcs:Definition");

            hasCorrectContent = true;
        }


        pt = e.getChild("NoValues",WCS.OWS_NS);
        if(pt!=null){
            if(hasCorrectContent)
                throw new WcsException(errMsg, WcsException.INVALID_PARAMETER_VALUE,"wcs:Definition");

            hasCorrectContent = true;
        }


        pt = e.getChild("ValuesReference",WCS.OWS_NS);
        if(pt!=null){
            if(hasCorrectContent)
                throw new WcsException(errMsg, WcsException.INVALID_PARAMETER_VALUE,"wcs:Definition");

            if(pt.getAttribute("reference",WCS.OWS_NS)==null){
                throw new WcsException("ows:ValuesReference MUST have an ows:reference", WcsException.MISSING_PARAMETER_VALUE,"ows:reference");
            }
            hasCorrectContent = true;
        }


        if(!hasCorrectContent){
            throw new WcsException(errMsg, WcsException.INVALID_PARAMETER_VALUE,"wcs:Definition");

        }





    }

    public static void checkAllowedValues(Element e) throws WcsException{

        boolean hasValue = false;
        checkNamespace(e,"AllowedValues",WCS.OWS_NS);

        Iterator i = e.getChildren("Value",WCS.OWS_NS).iterator();
        if(i.hasNext())
            hasValue = true;


        i = e.getChildren("Range",WCS.OWS_NS).iterator();
        while(i.hasNext()){
            checkOwsRange((Element)i.next());
            hasValue = true;
        }

    }

    public static void checkOwsRange(Element e) throws WcsException{
        checkNamespace(e,"Range",WCS.OWS_NS);
        //@todo QC ows:Range

        

    }

    public static void checkInterpolationMethods(Element e) throws WcsException {
        checkNamespace(e,"InterpolationMethods",WCS.WCS_NS);
        if(e.getChild("Default",WCS.WCS_NS)==null){
                throw new WcsException("wcs:InerpolationMethods MUST contain a wcs:Default element",
                        WcsException.MISSING_PARAMETER_VALUE,"wcs:Default");
        }


    }

      */
        
    public static NewBoundingBox getSubsetBoundingBox(
            HashMap<String, DimensionSubset> dimensionSubsets,
            TemporalDimensionSubset temporalSubset,
            NewBoundingBox coverageBoundingBox)
            throws WcsException {

        Logger log = LoggerFactory.getLogger("WCS");

        LinkedHashMap<String, CoordinateDimension> cvrgDims = coverageBoundingBox.getDimensions();

        LinkedHashMap<String, CoordinateDimension> subsetBBDims = new LinkedHashMap<>();
        double min, max;

        Date startTime=null, endTime=null;

        for(CoordinateDimension cDim : cvrgDims.values()) {

            String dimName = cDim.getName();

            DimensionSubset dimSubset = dimensionSubsets.get(dimName);

            if(dimSubset==null){
                // no subset on this dim? then we take the extents of the dimension in the coverage.
                min = cDim.getMin();
                max = cDim.getMax();
                CoordinateDimension newDim = new CoordinateDimension(dimName,min,max);
                subsetBBDims.put(dimName,newDim);
            }
            else {
                if(dimSubset instanceof TemporalDimensionSubset){
                    log.warn("getSubsetBoundingBox() - Found TemporalDimensionSubset in the dimensionsSubsets list.");
                }
                else {
                    if (dimSubset.isTrimSubset()) {
                        min = Double.parseDouble(dimSubset.getTrimLow());
                        max = Double.parseDouble(dimSubset.getTrimHigh());
                    } else {
                        // looks like a slice
                        min = Double.parseDouble(dimSubset.getSlicePoint());
                        max = min;

                    }
                    CoordinateDimension newDim = new CoordinateDimension(dimName, min, max);
                    subsetBBDims.put(dimName, newDim);
                }
            }

        }

        if(temporalSubset!=null) {
            if (temporalSubset.isTrimSubset()) {
                if (temporalSubset.isValueSubset()) {
                    startTime = temporalSubset.getStartTime();
                    endTime   = temporalSubset.getEndTime();
                }
                else { // It's an array index subset - oh. crap.
                    log.warn("Array subsetting not of time is not fully supported.");
                    startTime = coverageBoundingBox.getStartTime();
                    endTime = coverageBoundingBox.getEndTime();

                }
            }
            else { // It's a slice point.
                if (temporalSubset.isValueSubset()) {
                    startTime = temporalSubset.getSlicePointTime();
                    endTime = temporalSubset.getSlicePointTime();
                }
                else { //it's an array index subset. oh. crap.
                    log.warn("Array subsetting not of time is not fully supported.");
                    startTime = coverageBoundingBox.getStartTime();
                    endTime = coverageBoundingBox.getEndTime();

                }
            }
        }
        else if(coverageBoundingBox.hasTimePeriod()){
            startTime = coverageBoundingBox.getStartTime();
            endTime = coverageBoundingBox.getEndTime();
        }
        else {
            log.warn("getSubsetBoundingBox() - Neither the Coverage BoundingBox nor the specified subset contain " +
                    "information about Time bounds. SKIPPING time stuff...");
        }

        NewBoundingBox nbb = new NewBoundingBox(subsetBBDims,startTime,endTime,null);

        return nbb;

    }







}
