package ProducerConsumer.forStudent;

// BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//���̃N���X�͎��ƂŐ�������KeywordSearch.java�̃R�s�[�݂����Ȃ��̂Ȃ̂ŁC
//�R�s�[�ł��Ă���΁C2�_�D
public class FactoringMany {
	private static final int  SIZE = 100; //�u���b�L���O�L���[�ɒu���鍇�����̍ő��.

	// �f����������S������X���b�h�̖{��.
	private static final int NUM_WORKERS = Runtime.getRuntime().availableProcessors() - 2 ;

	public static void main(String[ ] args ) {
		//�f��������S���X���b�h�̖{����\��
		System.out.println("#FactoringThreads: " + NUM_WORKERS) ;

		// �w�肳�ꂽ�T�C�Y�̃u���b�L���O�L���[���쐬����
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>( SIZE ) ;

		// �������񋓃X���b�h�̎d���𐶐�����.
		EnumCompTask et = new EnumCompTask(queue) ;

		// �������̗񋓂�S������X���b�h��1�{�쐬����D
		Thread enumerator = new Thread( et ) ;

		// �f��������S���X���b�h���������񐶐�����D
		Thread[ ] workers = new Thread[NUM_WORKERS]; //�f��������S���X���b�h���o����z��𐶐�.
		for (int i = 0 ; i < NUM_WORKERS ; i++) {
			workers[i] = new Thread( new FactoringTask(queue) ) ; //i�Ԗڂ̑f��������S���X���b�h���쐬����.
		}

		// �f��������S���X���b�h���N������D
		for (int i = 0 ; i < NUM_WORKERS ; i++)
			workers[i].start( ) ; // i�Ԗڂ̑f��������S���X���b�h���N������.

		// �������̗񋓂�S������X���b�h���N������D
		enumerator.start( ) ;
	} // main���\�b�h�̏I���
}