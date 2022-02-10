import java.util.Scanner;

public class mainClass {
	
	static Scanner input = new Scanner(System.in);
	static item[] itemsHere = new item[10];     //this is the item list
	static basket[] basketHere = new basket[10];      //this is the list of items in the user's basket
    static int i;
    static String select = "";
    static int totalPrice = 0;        //the total price of the user's basket
    static int payment = 0;          //the amount of money the user decides to pay at the end

	public static void main(String[] args) {
		
		for(int j = 0; j<9;j++)//generates the item and basket spaces, currently empty
		{
			itemsHere[j] = new item();
			itemsHere[j].code = " 0" + (j + 1);
			itemsHere[j].quantity = 50;
			basketHere[j] = new basket();
		}
		
		itemsHere[0].name = "Pizza       ";   // all this code is to fill the stock list
		itemsHere[0].price = 10;
		itemsHere[1].name = "Burger       ";
		itemsHere[1].price = 7;
		itemsHere[2].name = "Sandwich     ";
		itemsHere[2].price = 5;
		itemsHere[3].name = "Water        ";
		itemsHere[3].price = 1;
		itemsHere[4].name = "Soft Drink   ";
		itemsHere[4].price = 2;
		itemsHere[5].name = "Tea          ";
		itemsHere[5].price = 2;
		itemsHere[6].name = "Coffee       ";
		itemsHere[6].price = 2;
		itemsHere[7].name = "Ice cream    ";
		itemsHere[7].price = 2;
		itemsHere[8].name = "Chocolate    ";
		itemsHere[8].price = 2;               // up till here
		
		while(true)//loop until the user selects "E" to exit 
		{
		System.out.println("Hello, customer, welcome to our restaurant \n" + 
		                    "Press B to buy items or R to remove them from your basket or S to look at your basket \n" +
					        "Alternatively, press C to check stocks, F to finalize payment or E to exit. \n");
		System.out.print("Input choice: ");
		select = input.next();                  //the user's choice
		if(select.contentEquals("B"))           //if the user selects B, the stocks are shown and the user selects one item code and its quantity
		{
			System.out.println("Below you have a list of everything currently available \n" +
			                   "Please choose a code and quantity \n");
			ShowItems();
			System.out.print("Code: ");
			i = input.nextInt();
			System.out.print("Quantity: ");
			int qtyRemove = input.nextInt();
			itemsHere[i-1].removeItem(qtyRemove);       //removes the item from the stock list 
			basketHere[i-1].AddItem(itemsHere[i-1].price, qtyRemove, itemsHere[i-1].name);    //adds the item to the basket
		}
		else if(select.contentEquals("S")) //shows the basket
		{
			System.out.println("This is your basket: ");
		    ShowBasket();
		}
		else if(select.contentEquals("R"))            //if the user selects R, the basket is shown and the user selects one item code and its quantity
		{
			ShowBasket();
			System.out.println("Please choose a code and quantity of the item you wish to remove");
			System.out.print("Code: ");
			i = input.nextInt();
			System.out.print("Quantity: ");
			int qtyRemove = input.nextInt();
			itemsHere[i-1].removeItem(-qtyRemove);     //basically removes the negative quantity from the stock list, which means it will actually add it back
			basketHere[i-1].AddItem(itemsHere[i-1].price, -qtyRemove, itemsHere[i-1].name);         //adds the negative amount of items in the basket, which means it will actually remove them
			
		}
		else if(select.contentEquals("C"))  //shows the stocks
		{
			ShowItems();
		}
		else if(select.contentEquals("F")) //here the user will finalize the payment
		{
			AddPrices();
			System.out.println("The total value of your basket is: " + totalPrice);   //shows the total value of the basket
			System.out.println("Please input the amount of money to be paid: ");
			payment = input.nextInt();             //user inputs the amount of money they wish to pay
			if(payment >= totalPrice)              // if the payment money is greater or equal to that of the basket value, the purchase is successful
			{
				System.out.println("Payment successful, enjoy your food!");
				if(payment != totalPrice)           //if the payment is larger than the basket value, change will also be given
				{
				     System.out.println("Here's your change: " + (payment - totalPrice));
				}
				break;
			}
			else       //otherwise, payment fails and the application quits
			{
				System.out.println("Payment unsuccessful, have a nice day!");
				break;
			}
			
		}
		else if(select.contentEquals("E"))     //if the user inputs E, they quit the application without paying and buying the items
		{
			System.out.println("Have a nice day!");
			break;
		}

		}
	}
	
	public static void ShowItems()       //method for displaying the list of items in stock
	{
		System.out.println("Code   " + "Item           " + "Price    " + "Quantity");
		System.out.println("-----------------------------------------");
		for(int j = 0; j<9; j++)
		{
			System.out.println(itemsHere[j].code + "    " + itemsHere[j].name +"     " + itemsHere[j].price + "         " + itemsHere[j].quantity);
		}
	}
	
	public static void ShowBasket()      //method for displaying the list of items in the basket
	{
		System.out.println("Code   " + "Item           " + "Price    " + "Quantity");
		System.out.println("-----------------------------------------");
		for(int j = 0; j<9; j++)
		{
			System.out.println("0" + (j + 1) + "    " + basketHere[j].name +"     " + basketHere[j].price + "         " + basketHere[j].quantity);
		}
	}
	
	public static void AddPrices() // method for adding all the prices for the final payment
	{
		for(int j = 0; j<9; j++)
		{
			totalPrice += basketHere[j].price * basketHere[j].quantity;
		}
	}

}

class item        //class for our item, each item having a name, price, code and quantity
{
	int price, quantity;
	String name, code;
	
	public void removeItem(int qty)   // method for removing quantity of the selected item
	{
		
		if(quantity - qty >= 0) //if there is enough in stock, proceed
		{
			quantity-= qty;
			if(qty < 0) //if the quantity input is negative, the item will be removed from the basket
			{
				System.out.println(name + " removed from basket!");
			}
			else //otherwise it is added!
			{
			    System.out.println(name + " added to basket!");
			}
		}
		else                 //otherwise, don't
		{
			System.out.println("Not enough " + name +  " in stock right now.");
			return;
		}
	}
}

class basket  //class for the basket, similar to the items one, but without the code
{
	int price = 0, quantity = 0;
	String name = "";
	
	public void AddItem(int pr, int qty, String nom) // method for adding quantity to the basket
	{
		price = pr;
		quantity += qty;
		name = nom;
	}
}







