package Builder.bank ;

public  class   WithdrawTask implements  Runnable {  
     private int delay ;  //�������Ƃ��Ԋu���o����C���X�^���X�E�t�B�[���h  
     private BankAccount account; // �������Ƃ�������ԍ����o����C���X�^���X�E�t�B�[���h 
     private long  amount ;  // �������Ƃ����z���o����C���X�^���X�E�t�B�[���h
     private int  times ;  // �������Ƃ��̉񐔂��o����C���X�^���X�E�t�B�[���h


    // �R���X�g���N�^�i�������Ƃ���̌����C���z�C�񐔂�����������j 
     public WithdrawTask(BankAccount account, long amount, int times, int delay) {  
         this.account = account ; 
         this.amount = amount ; 
         this.times = times ;  
         this.delay = delay ;
     }  


    // �������Ƃ����s�����\�b�h 
     public void run( ) {
         try {
             for ( int i = 1; i <= times;  i++ ) {  
                 account.withdraw(amount); // 1��������Ƃ�    
                 Thread.sleep(delay);  // �A������������Ƃ��ł�0.001�b�Ԃ̊Ԋu��u�� 
             }
         } catch (InterruptedException e) {
        	 Thread.currentThread().interrupt();
         }
     }
}