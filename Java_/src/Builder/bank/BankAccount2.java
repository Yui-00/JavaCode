package Builder.bank ;

// Lock�C���^�[�t�F�[�X��ReentrantLock�N���X�������̂Ŏ���1�s���K�v
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public  class   BankAccount2 extends BankAccount {
	// ���̃N���X��1�ȏ�̃��\�b�h���X���b�h�Z�[�t�ɂ������Ƃ��C���̃N���X�̃t�B�[���h
	// �Ƃ��āC���̍s�̂悤�� Lock�C���^�[�t�F�[�X�i�����������N���X�j�^�̕ϐ���ǉ�����
	private Lock  balanceChangeLock;

	// �R���X�g���N�^�i�V�����������J���j
	public BankAccount2( ) {
		// �R���X�g���N�^��ReentrantLock�N���X�̃C���X�^���X�i���b�N�E�I�u�W�F�N�g�j�𐶐�
		balanceChangeLock = new ReentrantLock( ) ;
	}


	// ������a���郁�\�b�h
	@Override
	public void deposit(long amount) {
		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������ɓ���O�Ɍ���������
		balanceChangeLock.lock( ) ;

		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������� try�u���b�N�ɓ����
		// finally�u���b�N�� �����O��.
		try {
			super.deposit(amount) ;
		} finally {
			balanceChangeLock.unlock( ) ; // �����O��
		}
	}


	// �������Ƃ����s�����\�b�h
	@Override
	public void withdraw(long amount) {
		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������ɓ���O�Ɍ���������
		balanceChangeLock.lock( ) ;

		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������� try�u���b�N�ɓ����
		// finally�u���b�N�� �����O��.
		try {
			super.withdraw(amount) ;
		} finally {
			balanceChangeLock.unlock( ) ; // �����O��
		}
	}

	// �c�����X�V���郁�\�b�h
	@Override
	public void setBalance(long newBalance) {
		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������ɓ���O�Ɍ���������
		balanceChangeLock.lock( ) ;

		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������� try�u���b�N�ɓ����
		// finally�u���b�N�� �����O��.
		try {
			super.setBalance(newBalance) ;
		} finally {
			balanceChangeLock.unlock( ) ; // �����O��
		}
	}
} // BankAccount2�N���X�̏I���