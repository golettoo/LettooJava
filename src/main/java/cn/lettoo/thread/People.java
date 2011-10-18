package cn.lettoo.thread;


public class People {

    private ThreadLocal<Integer> age = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            return 0;
        }
    };
    
    public int getAge() {
        return this.age.get();
    }
    
    public void setAge(int age) {
        this.age.set(age);
    }
}
