import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import {BrowserRouter, Route, Routes, Navigate} from 'react-router-dom';

import { SignUpPage } from './components/SignUpPage';
import { SignInPage } from "./components/SignInPage";
import { HomePage } from "./components/HomePage";
import {CreateShopPage} from "./components/CreateShopPage";
import {ShopPage} from "./components/ShopPage";
import {CreateTile} from "./components/CreateTile";
import {CategoryPage} from "./components/CategoryPage";
import {ItemPage} from "./components/ItemPage";

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="/signUp" replace/>} />
                <Route path="/signUp" element={<SignUpPage/>} />
                <Route path="/signIn" element={<SignInPage/>} />
                <Route path="/shops" element={<ShopPage/>} />
                <Route path="/shopList/createShop" element={<CreateShopPage/>} />
                <Route path="/categories" element={<CategoryPage/>}/>
                <Route path="/items" element={<ItemPage/>}/>
                <Route path="/home" element={<HomePage/>} />
                <Route path="*" element={<Navigate to="/signUp" replace/>} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
