package com.maple.selectedcamelwords;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.ex.EditorSettingsExternalizable;
import org.jetbrains.annotations.NotNull;

/**
 * @author maple
 * @since 2024/06/17
 */
public class SelectedCamelWordsAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // 获取到编辑器设置
        EditorSettingsExternalizable editorSettingsExternalizable = EditorSettingsExternalizable.getInstance();
        // 对之前的状态取反
        editorSettingsExternalizable.setCamelWords(!editorSettingsExternalizable.isCamelWords());
    }
}
