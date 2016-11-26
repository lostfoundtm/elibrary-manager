package com.sydor.elibrary.console;

public interface CommandLine {
    String readLine();

    void newLine();

    void skipLines(int number);

    void print(String msg);

    void println(String msg);
}
