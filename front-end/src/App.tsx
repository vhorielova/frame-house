import { Outlet } from "react-router-dom";
import Navbar from "./common/Navbar";
import "./App.css"


export default function App() {
  return <div className="page">

    <Navbar/>

    <main className="content">
      <Outlet />     
    </main> 
    
  </div>
}