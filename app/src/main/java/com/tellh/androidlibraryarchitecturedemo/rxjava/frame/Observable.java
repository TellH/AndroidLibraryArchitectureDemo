package com.tellh.androidlibraryarchitecturedemo.rxjava.frame;


public class Observable<T> {
    protected OnAttach<T> onAttach;

    public Observable(OnAttach<T> onAttach) {
        this.onAttach = onAttach;
    }

    public static <T> Observable<T> create(OnAttach<T> onAttach) {
        return new Observable<>(onAttach);
    }


    public void attach(Observer<T> observer) {
        onAttach.call(observer);
    }

    public <R> Observable<R> map(IFun<? super T, ? extends R> fun) {
        OperatorMap<T,R> operatorMap = new OperatorMap<>(fun);
        //根据操作符生成新的Observable,并返回,以便实现链式操作
        return lift(operatorMap);

    }

    public interface OnAttach<T> {
        void call(Observer<? super T> observer);
    }

    //重点,该方法的实现了方法链.
    public <R> Observable<R> lift(final Operator<? extends R, ? super T> operator) {
        return new Observable<>(new OnAttach<R>() {
            @Override
            public void call(Observer<? super R> observer) {
                Observer<? super T> call = operator.call(observer);
                Observable.this.onAttach.call(call);
            }
        });
    }

    //1.操作符接口
    public interface Operator<R, T> extends IFun<Observer<? super R>, Observer<? super T>> {}

}