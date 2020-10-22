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
package opendap.http.mediaTypes;

import opendap.bes.dap4Responders.MediaType;

/**
 * Comma Separated Values
 * https://tools.ietf.org/html/rfc4180
 *
 */
public class TextCsv extends MediaType {
    public static final String NAME = "csv";

    public static final String PRIMARY_TYPE = "text";
    public static final String SUB_TYPE = "csv";


    public TextCsv(){
        this("."+ NAME);
        setName(NAME);
    }

    public TextCsv(String typeMatchString){
        super(PRIMARY_TYPE,SUB_TYPE, typeMatchString);
    }

}
