import Header from "./components/header/Header.tsx";
import './App.css'
import {Route, Routes} from "react-router-dom";
import AddPage from './pages/AddPage.tsx';
import HomePage from './pages/HomePage.tsx';
import DetailsPage from "./pages/DetailsPage.tsx";
import EditPage from "./pages/EditPage.tsx";

function App() {

    return (
        <>
            <Header />
            <Routes>
                <Route path={"/"} element={<HomePage />} />
                <Route path={"insurances/add"} element={<AddPage/>} />
                <Route path={"/details/:type/:id"} element={<DetailsPage/>} />
                <Route path={"/details/:type/:id/edit"} element={<EditPage/>} />
            </Routes>
        </>
    )
}

export default App