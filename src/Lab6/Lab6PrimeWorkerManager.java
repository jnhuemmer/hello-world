package Lab6;

import java.util.concurrent.Semaphore;


// Object for managing prime number calculation threads. This ensures the GUI does not freeze
public class Lab6PrimeWorkerManager implements Runnable
{
	private int numWorkers;
	private int thePrimeNumber;
	
	public Lab6PrimeWorkerManager(int numWorkers, int thePrimeNumber)
	{
		this.numWorkers = numWorkers;
		this.thePrimeNumber = thePrimeNumber;
	}
	
	@Override
	public void run()
	{
		long startTime = System.currentTimeMillis();
		Semaphore semaphore = new Semaphore(this.numWorkers);
		
		// For loop goes through each number underneath user's input number (except 1)
		for (int i = 2; i <= this.thePrimeNumber; i++)
		{	
			if (Lab6PrimeGui.primeRun == true) // Check for cancel button
			{	
			    // Updates GUI every 1000 numbers
				if (i % 1000 == 0)
			    {
			     	Lab6PrimeGui.updateTime(((System.currentTimeMillis() - startTime) / 1000f) + " Secs");
			     	Lab6PrimeGui.updateNumbers(Lab6PrimeGui.primeList.size());
			    }
				try 
			    {
					semaphore.acquire();
			        Lab6PrimeWorker worker = new Lab6PrimeWorker(i, semaphore); // Calculates result for individual prime number
					worker.start();
			    } 
			    catch (InterruptedException e) 
			    { 
			        System.out.println(e);
			    }
			}
			else 
			{
				break;
			}
		}
		
		// Re-aquires all threads after the calculation to ensure every number is checked
		int returnedThreads = 0;
		while( returnedThreads < numWorkers )
		{
			try
			{
				semaphore.acquire();
				returnedThreads++;
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		
		// Post-job GUI updates
		Lab6PrimeGui.fillList();
		Lab6PrimeGui.updateTime(((System.currentTimeMillis() - startTime) / 1000f) + " Secs");
	 	Lab6PrimeGui.updateNumbers(Lab6PrimeGui.primeList.size());
	 
		if (Lab6PrimeGui.primeRun == true)
		{
			Lab6PrimeGui.primeRun = false;
		 	Lab6PrimeGui.updateStatus("Job Complete");
		}
		else
		{
			Lab6PrimeGui.updateStatus("Job Canceled");
		}
	 	
		Lab6PrimeGui.threadControl.setEnabled(true);
	 	Lab6PrimeGui.startButton.setEnabled(true);
	 	Lab6PrimeGui.cancelButton.setEnabled(false);
	}
	
	public void updatePrime(int newPrimeNum)
	{
		this.thePrimeNumber = newPrimeNum;
	}
	
	public void updateWorkers(int newWorkers)
	{
		this.numWorkers = newWorkers;
	}
}
