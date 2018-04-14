class Paralela implements Runnable {

	public String id;

	public Paralela(String id) {
		this.id = id;
		(new Thread(this)).start(); /* auto-start */
	}

	public void run() {
		for (int i = 0; i < 10; ++i)
			System.out.println("[" + id + "]");
	}

}

class Principal {

	public static void main(String[] args) {
		new Paralela("a");
		new Paralela("b");
		System.out.println("THE END");
	}

}
