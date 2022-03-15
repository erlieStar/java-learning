package com.javashitang.oom;

/**
 * @author lilimin
 * @since 2022-03-15
 */
public class StackOom {

    private static int stackLength = 0;

    // -Xss2M
    public static void main(String[] args) {
        StackOom stackOom = new StackOom();
        try {
            stackOom.invoke();
        } catch (Throwable e) {
            System.out.println("stack length: " + stackLength);
            throw e;
        }
    }

    public void invoke() {
        stackLength++;
        invoke();
    }
}
