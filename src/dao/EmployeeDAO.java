package dao;

import java.sql.Date;
import java.util.ArrayList;

import entity.EmployeeBean;
import exception.ServletServiceException;

public class EmployeeDAO {
	/**
	 * 従業員情報にかかわる全レコードの取得 ---返り値:ArrayList(EmployeeBean)
	 * @return
	 */
		public ArrayList<EmployeeBean> employeeAllGet() {
			ArrayList<EmployeeBean> emp_list = new ArrayList<EmployeeBean>();
			EmployeeBean employee = new EmployeeBean();

			employee.setEmp_code("A001");
			employee.setL_name("田中");
			employee.setF_name("一郎");
			employee.setL_kana_name("タナカ");
			employee.setF_kana_name("イチロウ");
			employee.setSex((byte)0);
			employee.setBirth_day(Date.valueOf("1986-4-20"));
			employee.setSection_code("01");
			employee.setEmp_date(Date.valueOf("2010-8-20"));

			employee.setSection_name("総務部");

			emp_list.add(employee);

			employee.setEmp_code("A002");
			employee.setL_name("山田");
			employee.setF_name("花子");
			employee.setL_kana_name("ヤマダ");
			employee.setF_kana_name("ハナコ");
			employee.setSex((byte)1);
			employee.setBirth_day(Date.valueOf("1976-2-10"));
			employee.setSection_code("01");
			employee.setEmp_date(Date.valueOf("2003-4-10"));

			employee.setSection_name("営業部");


			emp_list.add(employee);

			return emp_list;
		}




		/**
		 * 従業員情報の登録 ---Bean内のm_employeeとt_get_licenseの情報のみ登録されます
		 * @param employee
		 * @throws ServletServiceException
		 */
		public void employeeInsert(EmployeeBean employee) throws ServletServiceException {

		}




		/**
		 * 従業員情報の更新 ---Bean内のm_employeeとt_get_licenseの、emp_code以外の情報のみ更新します
		 * @param employee
		 * @throws ServletServiceException
		 */
		public void employeeUpdate(EmployeeBean employee) throws ServletServiceException {

		}




		/**
		 * 従業員情報の削除 ---m_employeeとt_get_licenseから、Bean内のemp_idを元に情報を削除します
		 * @param employee
		 * @throws ServletServiceException
		 */
		public void employeeDelete(EmployeeBean employee) throws ServletServiceException {

		}

}
