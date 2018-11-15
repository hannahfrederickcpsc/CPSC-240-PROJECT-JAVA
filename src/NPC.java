import java.util.ArrayList;
import java.util.Scanner;
class NoNPCException extends Exception{}
public abstract class NPC{
	private String properName;
	private ArrayList<String> dialogue;
	private int level;
	private int health;
	private String type;
	private ArrayList<Item>inventory;

	public NPC(Scanner s){}
	public String speak(){}
	public int getHealth(){}
	public int getLevel(){}
	public ArrayList<Item> getInventory(){}
	public abstract String getType();
	public abstract void setType();
}


