import {createGlobalStyle} from "styled-components";
import {BrowserRouter, Route} from "react-router-dom";
import LoginComp from "./login/LoginComp";

function App() {
  return (
    <>
      <GlobalStyle />
      <BrowserRouter>
        <Route exact path='/' component={LoginComp}/>
      </BrowserRouter>
    </>
  );
}

export default App;

export const GlobalStyle = createGlobalStyle`
	body {
  font-family: "Noto Sans KR", sans-serif;
  background-color: #eee;
  min-width: 1000px;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0;
  }
  
  input {
    outline: none;
  }
  
  input:focus {
    border: 2px solid crimson;
  }
`;