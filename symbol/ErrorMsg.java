//Taken from book
public class ErrorMsg
{
	boolean anyErrors;
	void complain(String msg)
	{
		if (!anyErrors)
			System.out.println("Errors founded!");
		anyErrors = true;
		System.out.println(msg);
	}
}
