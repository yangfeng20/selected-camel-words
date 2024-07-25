package com.maple.selectedcamelwords;

import com.sun.jna.platform.win32.User32;

import java.awt.event.KeyEvent;

/**
 * 关键状态检查器
 *
 * @author maple
 * @since 2024/07/25
 */
public class KeyStateChecker {

    private static final int VK_SHIFT = 0x10;
    private static final int VK_CONTROL = 0x11;
    private static final int VK_WINDOWS = 0x5B;

    public static boolean isKeyPressed(int keyCode) {
        return switch (keyCode) {
            case KeyEvent.VK_SHIFT -> (User32.INSTANCE.GetAsyncKeyState(VK_SHIFT) & 0x8000) != 0;
            case KeyEvent.VK_CONTROL -> (User32.INSTANCE.GetAsyncKeyState(VK_CONTROL) & 0x8000) != 0;
            case KeyEvent.VK_WINDOWS -> (User32.INSTANCE.GetAsyncKeyState(VK_WINDOWS) & 0x8000) != 0;
            default -> (User32.INSTANCE.GetAsyncKeyState(keyCode) & 0x8000) != 0;
        };
    }

    public static boolean openSelectedCamelWords() {
        boolean shiftPressed = isKeyPressed(KeyEvent.VK_SHIFT);
        boolean controlPressed = isKeyPressed(KeyEvent.VK_CONTROL);
        boolean winPressed = isKeyPressed(KeyEvent.VK_WINDOWS);
        return shiftPressed && controlPressed && winPressed;
    }
}