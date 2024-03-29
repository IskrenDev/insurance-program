import Header from "./components/header/Header.tsx";
import './App.css'
import {Route, Routes} from "react-router-dom";
import AddPage from './pages/AddPage.tsx';
import HomePage from './pages/HomePage.tsx';
import DetailsPage from "./pages/DetailsPage.tsx";
import EditPage from "./pages/EditPage.tsx";
import StatisticsPage from "./pages/StatisticsPage.tsx";
import SearchPage from "./pages/SearchPage.tsx";

function App() {

    return (
        <>
            <Header />
            <Routes>
                <Route path={"/"} element={<HomePage />} />
                <Route path={"insurances/add"} element={<AddPage/>} />
                <Route path={"/details/:type/:id"} element={<DetailsPage/>} />
                <Route path={"/details/:type/:id/edit"} element={<EditPage/>} />
                <Route path={"/insurances/statistics"} element={<StatisticsPage/>} />
                <Route path={"/insurances/search"} element={<SearchPage/>} />
            </Routes>
        </>
    )
}

export default App