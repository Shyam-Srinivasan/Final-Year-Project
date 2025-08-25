import {useEffect, useState} from "react";
import axios from "axios";
import "./StatsTile.css";

export const StatsTile = ({
                              name = "",
                          }) => {
    const [college, setCollege] = useState(null);
    const [data, setData] = useState(null);

    useEffect(() => {
        try {
            const savedCollege = JSON.parse(localStorage.getItem("college"));
            setCollege(savedCollege);
        } catch {
            setCollege(null);
        }
    }, []);

    useEffect(() => {
        const fetchData = async () => {

            if (!college?.id) return;

            let API_BASE;
            if (name === "Total Orders") {
                API_BASE = `http://${window.location.hostname}:8080/home/orders-college-id`;
            } else if (name === "Pending Orders") {
                API_BASE = `http://${window.location.hostname}:8080/home/pending-college-id`;
            } else if (name === "Completed Orders") {
                API_BASE = `http://${window.location.hostname}:8080/home/completed-college-id`;
            }
            try {
                const res = await axios.get(API_BASE, {
                    params: {collegeId: college.id},
                    validateStatus: () => true
                });
                if (res?.data != null) {
                    setData(res.data);
                }
            } catch {
                alert("Failed to fetch order details");
                setData("Error occurred");
            }
        };
        fetchData();
    }, [college, name]);

    return (
        <div className="notification">
            <div className="notiglow"/>
            <div className="notiborderglow"/>
            <div className="notititle">{name}</div>
            <div className="notibody">{data}</div>
        </div>
    );
}