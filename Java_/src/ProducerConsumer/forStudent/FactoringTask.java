package ProducerConsumer.forStudent;

// BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import java.util.concurrent.BlockingQueue;

//���̃N���X�͎��ƂŐ�������SearchTask.java�̃R�s�[�݂����Ȃ��̂Ȃ̂ŁC
//�R�s�[���ďC���ł��Ă���΁C4�_�D
//�ڂ����`�F�b�N����͎̂��Ԃ�������̂ŁC���ꂪ�����Ă������ł���� 4�_���_�ł����D
public class  FactoringTask implements Runnable {
	protected BlockingQueue<Integer> queue ;  // �u���b�L���O�L���[���w���ϐ�.

	// �R���X�g���N�^�i�f���������̎d�����P���j
	public FactoringTask(BlockingQueue<Integer> queue) {
		this.queue = queue; //�u���b�L���O�L���[�ւ̎Q�Ƃ��Ăяo�����̃v���O����������炤.
	}

	public void run( ) { //�f���������X���b�h�̍s���d��.
		try {
			while ( true ) { // ���L�������ƌJ��Ԃ��F
				int j = queue.take( ).intValue(); // �L���[���獇�������P����Ă���.

				if ( j == EnumCompTask.DUMMY_INT ) { // ����Ă����̂��_�~�[�Ȃ�,
					queue.put( EnumCompTask.DUMMY_INT ) ; // ���ꂪ�I���̍��}�Ȃ̂ŁC�L���[�ɖ߂��D
					return ; // �f�����������I�������D
				}

				//j�̑f�������������߂āC�P�̕�����ɋL���D
				StringBuilder sb = new StringBuilder(j + " = ") ;
				int half_j = j / 2 ; //j�̍ő�f�����̏�E
				boolean firstFactor = true ;
				int i = 2 ;
				while ( i <= half_j && i <= j ) {
					if ( j % i == 0) { //i��j�̑f�����ł���Ε�����ɒǉ�
						if (firstFactor) {
							sb.append(i + "") ;
							firstFactor = false ;
						} else {
							sb.append(" x " + i) ;
						}
						j /= i ;
					} else { //i��j�̑f�����ł����,
						i++ ;
					}
				}
				String result = sb.toString() ;

				//���ʂ�\��
				safePrintln(result) ;
			}  // while-���̏I���
		} catch ( InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	} // run���\�b�h�̏I���


	//���������ʂɕ\������X���b�h�Z�[�t�ȃ��\�b�h
	public void safePrintln(String s) {
		synchronized (System.out) {
			System.out.println(s);
		}
	}
} // FactoringTask.java �̏I���