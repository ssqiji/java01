package com.zhql.proxy01;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/17 10:03
 */
public abstract class AbstractHandler {
    /**
     * 责任链下一个处理单元
     */
    private AbstractHandler nextHandler;

    /**
     * 处理
     * @param targetMethod
     * @return
     * @throws Throwable
     */
    abstract Object invoke(TargetMethod targetMethod) throws Throwable;

    public Object process(TargetMethod targetMethod) throws Throwable {
        if(!hasNext()) {
            return targetMethod.getMethod().invoke(targetMethod.getTarget(), targetMethod.getArgs());
        }
        return nextHandler.invoke(targetMethod);
    }

    public boolean hasNext() {
        return nextHandler != null;
    }

    public static class HeadHandler extends AbstractHandler {
        @Override
        Object invoke(TargetMethod targetMethod) {
            return null;
        }
    }

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
