package by.epam.learn;

/**
 * Лабораторная работа №4 — система сборки проекта Maven.
 */
public class App
{
    public static void main( String[] args )
    {
        String name = (args.length > 0) ? args[0] : "Глеб";
        System.out.println( "Hello, " + name + "!" );
    }
}
