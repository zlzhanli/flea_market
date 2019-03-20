package com.flea.market.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 文件上传的视图
 * @author karl lee
 * @Date 2019/3/11
 */
public class FileView  implements Serializable {
    // 文件旧名称
    private String oldFileName;
    // 文件新名称
    private String newFileName;
    // 文件路径
    private String path;
    // 文件扩展名
    private String suffix;
    // 备注信息
    private String msg;

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileView fileView = (FileView) o;
        return Objects.equals(oldFileName, fileView.oldFileName) &&
                Objects.equals(newFileName, fileView.newFileName) &&
                Objects.equals(path, fileView.path) &&
                Objects.equals(suffix, fileView.suffix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldFileName, newFileName, path, suffix);
    }

    @Override
    public String toString() {
        return "FileView{" +
                "oldFileName='" + oldFileName + '\'' +
                ", newFileName='" + newFileName + '\'' +
                ", path='" + path + '\'' +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
