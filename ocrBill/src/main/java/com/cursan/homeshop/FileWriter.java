package com.cursan.homeshop;

public class FileWriter implements Writer {
    @Override
    public void start() {
        
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }

    @Override
    public void stop() {

    }
}
