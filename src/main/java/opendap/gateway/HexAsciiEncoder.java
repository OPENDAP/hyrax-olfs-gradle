/*
 * /////////////////////////////////////////////////////////////////////////////
 * // This file is part of the "Hyrax Data Server" project.
 * //
 * //
 * // Copyright (c) 2013 OPeNDAP, Inc.
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
package opendap.gateway;

import opendap.io.HyraxStringEncoding;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * User: ndp
 * Date: Apr 22, 2008
 * Time: 5:01:33 PM
 */
public class HexAsciiEncoder implements Encoder {

    public static String stringToHex(String s){
        StringBuilder es = new StringBuilder();

        byte[] b =  s.getBytes(HyraxStringEncoding.getCharset());
        for (byte aB : b) {
            if(aB < 0x10)
                es.append("0");
            es.append(Integer.toHexString(aB));
        }
        return es.toString();
    }


    public  String encode(String s){
        return stringToHex(s);
    }

    public  String decode(String s){
        return hexToString(s);
    }


    public  void encode(InputStream is, OutputStream os) throws Exception {
        stringToHex(is,os);
    }

    public  void decode(InputStream is, OutputStream os) throws Exception {
        hexToString(is,os);
    }


    public static void stringToHex(InputStream is, OutputStream os) throws Exception {
        boolean done = false;
        int b;

        while(!done){
            b = is.read();
            if(b<0)
                done = true;
            else
                os.write(Integer.toHexString(b).getBytes(HyraxStringEncoding.getCharset()));
        }
    }


    public static String hexToString(String s) throws NumberFormatException {

        if(s==null)
            return null;

        StringBuilder ds = new StringBuilder();
        String aChar;
        byte b;
        int i;


        for(i=0; i<s.length();i+=2){
            if(s.length() >= i+2)
                aChar = s.substring(i,i+2);
            else
                aChar = s.substring(i,i+1);
            b = Byte.parseByte(aChar,16);
            ds.append(String.valueOf((char) b));
        }

        return ds.toString();
    }

    public static void hexToString(InputStream is, OutputStream os) throws Exception {
        boolean done = false;
        byte[] b = new byte[2];
        int ret;
        String achar;
        byte b1;


        while(!done){
            ret = is.read(b);


            if(ret<0){
                done = true;
            }
            else if(ret!=0){

                achar = new String(b,HyraxStringEncoding.getCharset());
                //System.err.println("achar: "+achar);
                //System.err.println("ret: "+ret);

                b1 = Byte.parseByte(achar,16);
                os.write(String.valueOf((char)b1).getBytes(HyraxStringEncoding.getCharset()));
            }
        }

    }





    public static void main(String[] args) throws Exception {


        Options options = createCmdLineOptions();

        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse( options, args);

        if( ( cmd.hasOption("e") &&  cmd.hasOption("d")) ||
            (!cmd.hasOption("e") && !cmd.hasOption("d")) ) {
            printUsage(System.err);

        }

        if(cmd.hasOption("t")) {
            test();
        }
        else {
            String[] argz = cmd.getArgs();


            if(argz.length==0){
                if(cmd.hasOption("e")) {
                    stringToHex(System.in,System.out);
                }

                if(cmd.hasOption("d")) {
                    hexToString(System.in,System.out);
                }

            }
            else {
                for(String s : argz){

                    if(cmd.hasOption("e")) {
                        System.out.println(stringToHex(s));
                    }

                    if(cmd.hasOption("d")) {
                        System.out.println(hexToString(s));
                    }

                }
            }

        }



    }


    public static void test(){
        // byte[] b = {0,1,2,3,4,5,6,7,8,9,0xa,0xb,0xc,0xd,0xf};

        String tests[] = {
                           "http://www.google.com",
                            //new String(b),
                            "http://g0dup05u.ecs.nasa.gov/cgi-bin/ceopAIRX2RET?service=WCS&version=1.0.0&request=GetCoverage&coverage=H2OMMRStd&crs=WGS84&bbox=-107.375000,51.625000,-102.625000,56.375000&format=netCDF&TIME=2002-10-03&resx=0.25&resy=0.25&interpolationMethod=Nearest%20neighbor",
                            "foobar"

                         };

        for(String s : tests){
            System.out.println("source string:      "+s);
            System.out.println("   stringToHex():        "+ stringToHex(s));
            //System.out.println("          hexToString("+s+"): "+hexToString(s));
            System.out.println("   hexToString(stringToHex(): "+ hexToString(stringToHex(s)));
        }

    }



    private static Options createCmdLineOptions(){

        Options options = new Options();


        options.addOption("e", false, "encode the command line arguments, cannot be used in conjunction with -d (decode).");
        options.addOption("d", false, "decode the command line arguments, cannot be used in conjunction with -e (encode).");
        options.addOption("t", false, "runs internal tests and produces output on stdout.");

        return options;

    }


    private static void printUsage(PrintStream ps){

        ps.println("Usage:  ");
        ps.println("");
        ps.println("        UrlEncoder  -d -e -t [String1 String2...]");
        ps.println("");
        ps.println("Summary:");
        ps.println("    Encodes/decodes ASCII strings as the hexadecimal values");
        ps.println("    of the ASCII characters in the string. Only encoding");
        ps.println("    or decoding may happen at a time.");
        ps.println("");
        ps.println("");
        ps.println("Options:");
        ps.println("");
        ps.println("     -e   encode the command line arguments, may not be");
        ps.println("          used in conjunction with -d (decode).");
        ps.println("");
        ps.println("     -d   decode the command line arguments, cannot be");
        ps.println("          used in conjunction with -e (encode).");
        ps.println("");
        ps.println("     -t   Runs internal tests and produces output on");
        ps.println("          stdout. Using this option causes all other");
        ps.println("          options and inputs to be ignored.");
        ps.println("");


    }

}
