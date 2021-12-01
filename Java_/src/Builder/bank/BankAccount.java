package Builder.bank ;

public  class   BankAccount {
	private static volatile long nextNumber ;  // ���Ɏg��������ԍ��D�N���X�E�t�B�[���h

	//�C���X�^���X�E�t�B�[���h
	private long  number ;  // �����ԍ�
	private long  balance ;  // �����c��

	// �R���X�g���N�^�i�V�����������J���j
	public BankAccount( ) {
		number = nextNumber ; // �����ԍ����i���Ɏg����ԍ��Ɂj�ݒ�
		balance = 0 ; // �����c����������
		nextNumber++ ; // ���Ɏg����ԍ��𑝂₷
	}


	// ������a���郁�\�b�h
	public void deposit(long amount) {
		System.out.print("Depositing " + amount) ;  // �a�����z��\��
		long newBalance = balance + amount ; // �V�����c�����v�Z
		System.out.println(" , new balance is " + newBalance) ;  // �V�c����\��
		balance = newBalance ; // �����c�����X�V
	}

	// �����������o�����\�b�h
	public void withdraw(long amount) {
		if (amount > balance) {
			System.out.println("insufficient balance") ;  // �c���s����\��
		} else {
			System.out.print("Withdrawing " + amount) ;  // �����o���z��\��
			long newBalance = balance - amount ; // �V�c�����v�Z
			System.out.println(", new balance is " + newBalance) ;  // �V�c����\��
			balance = newBalance ; // �c�����X�V
		}
	}

	// �����ԍ���Ԃ����\�b�h
	public long getNumber( ) {
		return number; // �����ԍ���Ԃ�
	}

	// �c����Ԃ����\�b�h
	public long getBalance( ) {
		return balance ; // �����c����Ԃ�
	}

	// �c�����X�V���郁�\�b�h
	public void setBalance(long newBalance) {
		balance = newBalance ; // �����c�����X�V����
	}
} // BankAccount�N���X�̏I���
