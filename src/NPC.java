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
	public getHealth(){}
	public getLevel(){}
	public getInventory(){}
	public abstract String getType();
	public abstract void setType();
}


