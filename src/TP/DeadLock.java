package TP;

class monObjet {
    public monObjet() {}

    public synchronized void action1(monObjet o) {
        try {
            Thread.currentThread().sleep(200);
        } catch (InterruptedException ex) {
            return;
        }
        o.action1(this);
    }

    public synchronized void action2(monObjet o) {
        try {
            Thread.currentThread().sleep(200);
        } catch (InterruptedException ex) {
            return;
        }
        o.action2(this);
    }
}

class MonThread extends Thread {
    private monObjet obj1, obj2;

    public MonThread(monObjet o1, monObjet o2) {
        obj1 = o1;
        obj2 = o2;
    }

    public void run() {
        obj1.action1(obj2);
    }
}

class Deadlock {
    public static void main(String[] args) {
        monObjet o1 = new monObjet();
        monObjet o2 = new monObjet();
        MonThread t1 = new MonThread(o1, o2);
        t1.setName("t1");
        MonThread t2 = new MonThread(o2, o1);
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}

