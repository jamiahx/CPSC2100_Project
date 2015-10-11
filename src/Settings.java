import java.util.Scanner;

public final class Settings
{
    private static Scanner settingsSource = new Scanner("settings.txt");

    /// these need to be put behind a guard object
    private static String systemName = Scanner("System_Name.txt").next();
    /// Need to make a proper parser or method of configuring


    public static String getSystemName()
    {
	return systemName;
    }
}
