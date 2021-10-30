import axios from "axios";

const URL = "http://localhost:8080/"

/**
 * 로그인 정보를 전달
 * - 성공시 스케쥴 페이지로 이동
 * @param loginInfo
 * @return {Promise<AxiosResponse<any>>}
 */
export const loginService = (loginInfo) => {
  return axios.post(URL + "login", loginInfo);
}