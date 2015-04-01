package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.IClient;
import com.hundsun.t2sdk.interfaces.T2SDKException;

import com.hundsun.t2sdk.impl.configuration.DefaultConfigurationHelper;
import com.hundsun.t2sdk.interfaces.configuration.IConfiguration;

import java.util.Scanner;


public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IClient client = null;
		T2Services server = T2Services.getInstance();
		
		//DefaultConfigurationHelper configHelp=new DefaultConfigurationHelper();
		//IConfiguration configuration  = configHelp.loadFileToConfiguration("../../lib/t2sdk-config.xml");
		
		try {
			//server.init(configuration);
			
			server.init();
			server.start();
			
			
			client = server.getClient("ufx");
																	
			//���ûص��෢���õĽӿ�
			CallBack.setClient(client);
			
			Impl szImpl = new Impl(client);
			
			int iRet = szImpl.Login();

			
			if (iRet != 0)
			{
				System.out.println("Stop");
				server.stop();
				return;
			}
			
			
			
			System.out.print("1.����-֤ȯ�ɽ��ر�. 2.֤ȯί��  3.֤ȯί�в�ѯ  4.ί�г���   5.�ֲֲ�ѯ  6.�ʽ��ѯ   0.�˳���ϵͳ!\n��������Ĳ���:");
			

			InputStreamReader is_reader = new InputStreamReader(System.in);
			String str = null;
			
			int i = -1;
			
			while (i != 0)
            {          	
            	try	{
            		str = new  BufferedReader(is_reader).readLine();
            	}catch (IOException e){
        			e.printStackTrace();
        			continue;
        		}
            	
            	if (str == null || str.equals(""))
        		{
        			continue;
        		}
            	
            	try
            	{
            		i = Integer.valueOf(str);
            	}
        		catch (NumberFormatException e)
            	{
        			i = -1;
            		System.out.println("����Ϊ����ֵ������������: ");
            		continue;
            	}
            	
                switch (i)
                {
                    case 1:
                    	szImpl.sub_deal();
                        break;
                    case 2:
                    	szImpl.entrust();
                        break;

                    //��ί��
                    case 3:
                    	szImpl.query_entrust();

                        break;

                    //����
                    case 4:
                    	szImpl.entrust_withdraw();
                        break;

                    //��ֲ�
                    case 5:
                    	szImpl.SecuStkQry();
                        break;

                    //���ʽ�
                    case 6:
                    	szImpl.FundAllQry();
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("����ֵ��������������!");
                        break;
                }
				
                if (i == 0)
                {
                	break;
                }

                System.out.print("1.����-֤ȯ�ɽ��ر�. 2.֤ȯί��  3.֤ȯί�в�ѯ  4.ί�г���   5.�ֲֲ�ѯ  6.�ʽ��ѯ   0.�˳���ϵͳ!\n��������Ĳ���:");
            }
       
			server.stop();	
					
		} catch (T2SDKException e) {
			// TODO: handle exception
			//System.out.println("Exception!");
			e.printStackTrace();
		} 	
		
	}

}
