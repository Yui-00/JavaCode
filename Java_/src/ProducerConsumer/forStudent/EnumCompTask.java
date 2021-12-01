package ProducerConsumer.forStudent;

import java.util.Random;
// BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import java.util.concurrent.BlockingQueue;

//���̃N���X�͎��ƂŐ�������EnumerationTask.java�̃R�s�[�݂����Ȃ��̂Ȃ̂ŁC
//�R�s�[���ďC���ł��Ă���΁C4�_�D
//�ڂ����`�F�b�N����͎̂��Ԃ�������̂ŁC���ꂪ�����Ă������ł���� 4�_���_�ł����D
public class  EnumCompTask implements Runnable {
	protected final static int  DUMMY_INT = 2 ; //�񋓂̏I���������̂Ɏg����.
	protected BlockingQueue<Integer> queue ;  // �u���b�L���O�L���[���w���ϐ�.

	// �R���X�g���N�^�i�񋓂̎d�����P���j
	public EnumCompTask(BlockingQueue<Integer> queue) {
		this.queue = queue ; // �u���b�L���O�L���[���Ăяo�����̃v���O����������炤.
	}

	// �����X���b�h�̍s���d���i5000��10000��菬�����Ȃ��������������_���ɐ�������d���j
	public void run( ) {
		try {
			//5000�̍������i�e10000�ȏ�j�𐶐��D
			Random rand = new Random() ; //�^��������������쐬�D
			int i = 1 ;
			while (i <= 5000) {
				//10000��菬�����Ȃ������������_���ɐ���
				int j = 10000 + rand.nextInt(Integer.MAX_VALUE - 10000 ) ;
				if (j % 2 == 0) { //�����Ȃ��
					queue.put(j) ;//�������Ȃ̂ŃL���[�ɒu���D
					i++ ;
				} else { //��Ȃ�� �f�����𔻒�
					int bound = (int)Math.sqrt(j) ; //j�̍ŏ��f�����̏�E
					int k = 3;
					for ( ; k <= bound ; k += 2) {
						if (j % k == 0) break ; //�������Ȃ̂�for������o��D
					}
					if (k <= bound) {//�������Ȃ��
						queue.put(j) ;//�L���[�ɒu���D
						i++ ;
					}
				}
			}
			queue.put(DUMMY_INT); // �L���[�Ƀ_�~�[��u���ii.e., �񋓂̊����̍��}�𑗂�j.
		} catch ( InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
} // EnumCompTask.java �̏I���