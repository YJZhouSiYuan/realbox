package com.realbox.model.bean.program;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class PropertyBean {

    /**
     * taskId : 12345
     * taskKind : notify
     * replyId : 45678
     * taskName : programmeDistribute
     */
    private String taskId;
    private String taskKind;
    private String replyId;
    private String taskName;

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setTaskKind(String taskKind) {
        this.taskKind = taskKind;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
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

    public String getReplyId() {
        return replyId;
    }

    public String getTaskName() {
        return taskName;
    }
}
