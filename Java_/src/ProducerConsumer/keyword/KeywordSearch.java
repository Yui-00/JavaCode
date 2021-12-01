package ProducerConsumer.keyword ;

// File�N���X���g���̂ŕK�v
import java.io.File;
// Scanner�N���X���g���̂ŕK�v
import java.util.Scanner;
// BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public  class  KeywordSearch {
	private static final int  SIZE = 10;  // �u���b�L���O�L���[�ɒu����t�@�C���̍ő��.

	// ������S������X���b�h�̖{��.
	private static final int  SEARCHER = Runtime.getRuntime().availableProcessors() - 2 ;

	@SuppressWarnings("resource")
	public static void main(String[ ] args ) {
		//System.out.println("#availableThreads: " +
		//	Runtime.getRuntime().availableProcessors()) ;

		//�����X���b�h�̖{����\��
		System.out.println("#searchers: " + SEARCHER) ;

		// �w�肳�ꂽ�T�C�Y�̃u���b�L���O�L���[���쐬����
		final BlockingQueue<File> queue = new ArrayBlockingQueue<File>( SIZE ) ;

		Scanner in = new Scanner(System.in); // �L�[�{�[�h����̓��͂��s���X�L���i�[�𐶐�.

		System.out.println("���݃f�B���N�g��: " + new File(".").getAbsoluteFile().getParent()) ;
		System.out.print( "�J�n�f�B���N�g���F" );  //���p�҂ɊJ�n�f�B���N�g���̓��͂��Ñ�.
		String directory = in.nextLine( ) ; // �L�[�{�[�h����J�n�f�B���N�g������͂���D

		System.out.print( "�L�[���[�h�F" );  //���p�҂ɃL�[���[�h�̓��͂��Ñ�����.
		final String keyword = in.nextLine( ) ; // �L�[�{�[�h����L�[���[�h����͂���D

		File  startDirectory = new File( directory ) ; // �J�n�f�B���N�g���̒��ە\���𐶐�.

		// �񋓃X���b�h�̎d���𐶐�����.
		EnumerationTask et = new EnumerationTask(queue, startDirectory) ;

		// �t�@�C���̗񋓂�S������X���b�h��1�{�쐬����D
		Thread enumerator = new Thread( et ) ;

		// �����X���b�h���������񐶐�����D
		Thread[ ] searchers = new Thread[SEARCHER]; //�����X���b�h���o����z��𐶐�.
		for (int i = 0 ; i < SEARCHER ; i++) {
			searchers[i] = new Thread( new SearchTask(queue, keyword) ) ; // i�Ԗڂ̌����X���b�h���쐬����.
		}

		// �����X���b�h���N������D
		for (int i = 0 ; i < SEARCHER ; i++)
			searchers[i].start( ) ; // i�Ԗڂ̌����X���b�h���N������.

		// �t�@�C���̗񋓂�S������X���b�h���N������D
		enumerator.start( ) ;
	} // main���\�b�h�̏I���
} // KeywordSearch.java�̏I���