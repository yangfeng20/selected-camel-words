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
        private final EditorSettingsExternalizable editorSettingsExternalizable = EditorSettingsExternalizable.getInstance();

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {

            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_SHIFT:
                case KeyEvent.VK_CONTROL:
                case KeyEvent.VK_WINDOWS:
                    if (KeyStateChecker.openSelectedCamelWords()) {
                        editorSettingsExternalizable.setCamelWords(true);
                        break;
                    }
                    editorSettingsExternalizable.setCamelWords(false);
                    break;
                default:
                    break;
            }

            return false;
        }
    }
}
