import styled from "styled-components";
import {memo, useCallback, useState} from "react";
import {loginService} from "../service/ScheduleService";

const LoginComp = memo(({history}) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const onChangeId = useCallback((e) => {
    setEmail(e.target.value);
  }, []);
  const onChangePassword = useCallback((e) => {
    setPassword(e.target.value);
  }, []);

  const login = () => {
    if(email !== '' && password !== '') {
      const loginInfo = {
        email : email,
        password: password
      };

      loginService(loginInfo).then(res => {
        if(res.data == null) {
          alert("로그인 실패!");
        } else {
          alert("로그인 성공!");
          console.log(res);
        }
      });
    }
  };

  return (
      <Login>
        <Title>Ruby's Schedule</Title>
        <Input
          type="text"
          placeholder="email을 입력하세요"
          className="email"
          onChange={onChangeId}
          value={email}
        />
        <Input
          type="password"
          placeholder="PASSWORD를 입력하세요"
          className="password"
          onChange={onChangePassword}
          value={password}
        />
        <Button onClick={login}>로그인</Button>
      </Login>
  )
});

export default LoginComp;

const Login = styled.div`
  box-sizing: content-box;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #eee;
  padding: 20px;
`

const Title = styled.h1`
  margin-bottom: 40px;
`

const Input = styled.input`
  box-sizing: border-box;
  width: 300px;
  height: 40px;
  margin-bottom: 20px;
  padding: 0 5px;
  border: none;
  border-bottom: 1px solid rgb(80, 80, 80);
  border-radius: 0px;
  background-color: #eee;
  outline: none;
  font-size: 16px;
  
  &:focus {
    border: none;
    border-bottom: 1px solid rgb(80, 80, 80);
  }
`

const Button = styled.button`
  margin-top: 30px;
  box-sizing: border-box;
  width: 300px;
  height: 50px;
  border: 2px solid #222222;
  border-radius: 20px;
  font-size: 16px;
  
  &:active {
    background-color: rgb(196, 196, 196);
  }
`