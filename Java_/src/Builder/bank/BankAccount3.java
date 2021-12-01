package Builder.bank ;

public  class   BankAccount3 extends  BankAccount {
	// ������a���郁�\�b�h�i�������\�b�h�j
	@Override
	public synchronized  void deposit(long amount) {
		super.deposit(amount) ;
	}

	// �����������o�����\�b�h
	@Override
	public  synchronized  void withdraw(long amount) {
		super.withdraw(amount) ;
	}

	// �c�����X�V���郁�\�b�h
	public synchronized void setBalance(long newBalance) {
		super.setBalance(newBalance) ; // �����c�����X�V����
	}
} // BankAccount�N���X�̏I���
