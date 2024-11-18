package Lab6;

import java.util.concurrent.Semaphore;

// Calculates if a number is prime
public class Lab6PrimeWorker extends Thread
{
	private int primeNum = 7;
	private boolean isPrime = false;
	
	private final Semaphore semaphore;
	
	public Lab6PrimeWorker(int primeNum, Semaphore semaphore)
	{
		this.primeNum = primeNum;
		this.semaphore = semaphore;
	}
	
	// Calculation for if a number is prime
	private boolean checkPrime()
	{
		for (int i = 2; i < this.primeNum - 1; i++)
		{
			if (this.primeNum % i == 0)
			{
				return false;
			}
			if (Lab6PrimeGui.primeRun == false) // Cancel button flag
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean getIsPrime()
	{
		return this.isPrime;
	}
	
	public int getPrimeNum()
	{
		return this.primeNum;
	}
	
	public void setPrimeNum(int newPrime)
	{
		this.primeNum = newPrime;
	}
	
	@Override
	public void run()
	{
		try 
		{
			this.isPrime = checkPrime();
			System.out.println(this.primeNum + " is it prime? " + this.isPrime); 
			
			if (this.isPrime == true)
			{
				Lab6PrimeGui.primeList.add(this.primeNum);
			}
			semaphore.release();
		}
		
		catch(Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}
	}
	
	public static void main(String[] args)
	{
		Semaphore semaphore = new Semaphore(1);
		try
		{
			semaphore.acquire();
			Lab6PrimeWorker skeletronPrime = new Lab6PrimeWorker(7919, semaphore);
			skeletronPrime.run();
			System.out.println(skeletronPrime.getIsPrime());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
