public class InheritateSample {

    public abstract class Animal{
        String name;

        Animal(String name){
            this.name = name;
        }

        abstract void bark();

    }

    public class Dog extends Animal{

        Dog(String name) {
            super(name);
        }

        @Override
        void bark() {

            System.out.println("WanWan");
        }

    }


}
