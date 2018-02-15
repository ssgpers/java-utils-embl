package de.embl.cba.utils.fileutils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class PathMapper
{

    public static List< String > asEMBLClusterMounted( List< Path > paths )
    {
        ArrayList< String > newPaths = new ArrayList<>();

        for ( Path path : paths )
        {
            newPaths.add( asEMBLClusterMounted( path.toString() ) );
        }

        return newPaths;
    }

    public static String asEMBLClusterMounted( String string )
    {
        return asEMBLClusterMounted( Paths.get( string ) );
    }

    public static String asEMBLClusterMounted( File file )
    {
        return asEMBLClusterMounted( file.toPath() );
    }

    public static String asEMBLClusterMounted( Path path )
    {

        String pathString = path.toString();
        String newPathString = null;

        if ( isMac() )
        {
            newPathString = pathString.replace( "/Volumes/", "/g/" );
        }
        else if ( isWindows() )
        {
            try
            {
                Runtime runTime = Runtime.getRuntime();
                Process process = null;
                process = runTime.exec( "net use" );

                InputStream inStream = process.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader( inStream );
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );
                String line = null;
                String[] components = null;

                while ( null != ( line = bufferedReader.readLine() ) )
                {
                    components = line.split( "\\s+" );
                    if ( ( components.length > 2 ) && ( components[ 1 ].equals( pathString.substring( 0, 2 ) ) ) )
                    {
                        newPathString = pathString.replace( components[ 1 ], components[ 2 ] );
                    }
                }

                newPathString = newPathString.replace( "\\", "/" );
                newPathString = newPathString.replaceFirst( "//[^/]*/", "/g/" );

            }
            catch ( IOException e )
            {
                System.out.println( e.toString() );
            }
        }
        else
        {
            newPathString = pathString;
        }

        return newPathString; //Paths.get( newPathString );

    }

    public static String getOsName()
    {
        return System.getProperty("os.name");
    }

    public static boolean isWindows()
    {
        return getOsName().toLowerCase().contains( "win" );
    }

    public static boolean isMac()
    {
        String OS = getOsName();
        return ( OS.toLowerCase().contains( "mac" ) ) || ( OS.toLowerCase().contains( "darwin" ) );
    }

}
