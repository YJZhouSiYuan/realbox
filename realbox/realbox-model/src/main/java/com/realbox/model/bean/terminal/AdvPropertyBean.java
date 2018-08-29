package com.realbox.model.bean.terminal;

/**
 * Created by zph on 2017/12/28 0028.
 */
public class AdvPropertyBean {

    private String taskId;
    private String taskKind;
    private String taskName;
    private String replyId;

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setTaskKind(String taskKind) {
        this.taskKind = taskKind;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskKind() {
        return taskKind;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }
}
