package de.embl.cba.utils.fileutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class PathMapper
{

    public static Path asEMBLClusterMounted( Path path )
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

                newPathString = pathString.replace( "\\", "/" );
                newPathString = pathString.replaceFirst( "//.*/", "/g/" );

            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else
        {
            newPathString = pathString;
        }


        return Paths.get( newPathString );

    }

    public static String getOsName()
    {
        String OS = System.getProperty("os.name");
        return OS;
    }

    public static boolean isWindows()
    {
        return getOsName().startsWith("Windows");
    }


    public static boolean isMac()
    {
        String OS = getOsName();

        if ( ( OS.toLowerCase().indexOf( "mac" ) >= 0 ) || ( OS.toLowerCase().indexOf( "darwin" ) >= 0 ) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
