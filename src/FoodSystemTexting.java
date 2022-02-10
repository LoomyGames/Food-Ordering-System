import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FoodSystemTexting {
	
	String[] code;
	int[] price, quantity;
	int totalPrice;
	

	@Test
	void test() {
		code[0] = "01";
		price[0] = 10;
		quantity[0] = 2;
		code[1] = "02";
		price[1] = 5;
		quantity[1] = 1;
		code[2] = "07";
		price[2] = 2;
		quantity[2] = 3;
		mainClass MainClass = new mainClass();
		MainClass.payment = 40;
		for(int i = 0;i<3;i++)
		{
			totalPrice += price[i] * quantity[i];
		}
		assertTrue((totalPrice <= MainClass.payment), "Testing addition is incorrect");
		
	}

}
