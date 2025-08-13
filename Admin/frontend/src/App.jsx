import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import {BrowserRouter, Route, Routes, Navigate} from 'react-router-dom';

import { SignUpPage } from './components/SignUpPage';
import { SignInPage } from "./components/SignInPage";
import { HomePage } from "./components/HomePage";

function App() {
  return (
    <div className="App">
      {/*<SignUpPage/>*/}
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="/signUp" replace/>} />
                <Route path="/signUp" element={<SignUpPage/>} />
                <Route path="/signIn" element={<SignInPage/>} />
                <Route path="/home" element={<HomePage/>} />
                <Route path="*" element={<Navigate to="/signUp" replace/>} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
