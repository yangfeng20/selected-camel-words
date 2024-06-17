package com.maple.selectedcamelwords;

import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.editor.ex.EditorSettingsExternalizable;
import com.intellij.openapi.wm.IdeFrame;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author maple
 * @since 2024/06/17
 */
public class SelectedCamelWordsListener implements ApplicationActivationListener {

    private final KeyEventDispatcher dispatcher = new MyKeyEventDispatcher();

    @Override
    public void applicationActivated(@NotNull IdeFrame ideFrame) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
    }

    @Override
    public void applicationDeactivated(@NotNull IdeFrame ideFrame) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
    }

    private static class MyKeyEventDispatcher implements KeyEventDispatcher {
        private boolean shiftPressed = false;
        private boolean ctrlPressed = false;
        private boolean winPressed = false;

        private final EditorSettingsExternalizable editorSettingsExternalizable = EditorSettingsExternalizable.getInstance();

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            int keyCode = e.getKeyCode();
            int id = e.getID();

            if (id == KeyEvent.KEY_PRESSED) {
                switch (keyCode) {
                    case KeyEvent.VK_SHIFT:
                        shiftPressed = true;
                        break;
                    case KeyEvent.VK_CONTROL:
                        ctrlPressed = true;
                        break;
                    case KeyEvent.VK_WINDOWS:
                        winPressed = true;
                        break;
                }

                if (shiftPressed && ctrlPressed && winPressed) {
                    // Shift + Ctrl + Win 按下 开启驼峰选中
                    editorSettingsExternalizable.setCamelWords(true);
                }
            } else if (id == KeyEvent.KEY_RELEASED) {
                switch (keyCode) {
                    case KeyEvent.VK_WINDOWS:
                        if (shiftPressed && ctrlPressed) {
                            // Win 键抬起! 关闭驼峰选中
                            editorSettingsExternalizable.setCamelWords(false);
                        }
                        winPressed = false;
                        break;
                    case KeyEvent.VK_SHIFT:
                        shiftPressed = false;
                        break;
                    case KeyEvent.VK_CONTROL:
                        ctrlPressed = false;
                        break;
                }
            }
            // 继续分发事件
            return false;
        }
    }
}
