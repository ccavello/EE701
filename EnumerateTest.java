class EnumerateTest {
 void listCurrentThreads() {
  ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
  int numThreads;
  Thread[] listOfThreads;

  numThreads = currentGroup.activeCount();
  listOfThreads = new Thread[numThreads];
  currentGroup.enumerate(listOfThreads);
  for (int i = 0; i < numThreads; i++) {
   System.out.println("Thread #" + i + " = " + listOfThreads[i].getName());
  }
 }
}
