import { Outlet } from "react-router-dom";
import Navbar from "./common/Navbar";
import "./App.css"
import { Toaster } from "react-hot-toast";


export default function App() {
  return <div className="page">
      
    <Toaster 
      position="top-right" 
      containerStyle={{
        top: 80
      }}
     />

    <Navbar/>

    <main className="content">
      <Outlet />     
    </main> 
    
  </div>
}