package Builder.bank ;

public  class  Agent {
	protected static void testAccount(BankAccount account, long amount, int times, int delay) {
		// �^�X�N�i�a������Ƃ����d���j���P�������C���̃^�X�N��S���X���b�h���P��������
		Runnable dTask = new DepositTask(account, amount, times, delay) ;
		Thread dAgent = new Thread(dTask) ;

		// �^�X�N�i�������Ƃ��Ƃ����d���j���P�������C���̃^�X�N��S���X���b�h���P��������
		Runnable wTask = new WithdrawTask(account, amount, times, delay) ;
		Thread wAgent = new Thread(wTask) ;

		// ��قǐ��������Q�̃X���b�h���N������
		dAgent.start( ) ;
		wAgent.start( ) ;
	}

	public static void main(String[ ] args ) {
		testAccount(new BankAccount3(), 100, 1000, 1) ;
	}
}