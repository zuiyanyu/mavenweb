package com.spring_stu.springAnnotations;

public class AnnotationVO {
    private String annotationType;

    public AnnotationVO(String annotationType){
        this.annotationType = annotationType ;
    }

    public String getAnnotationType() {
        return annotationType;
    }

    public void setAnnotationType(String annotationType) {
        this.annotationType = annotationType;
    }

    @Override
    public String toString() {
        return "AnnotationVO{" +
                "annotationType='" + annotationType + '\'' +
                '}';
    }
}
