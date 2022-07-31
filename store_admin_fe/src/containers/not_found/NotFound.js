import styled from "styled-components"
import qs from "query-string"

const Background = styled.div`
  background-image: url(assets/media/error/bg1.jpg);
`

const NotFound = props => {
    const error = qs.parse(props.location.search).error;
    sessionStorage.clear();
    return (
        <Background className="d-flex flex-row-fluid flex-column bgi-size-cover bgi-position-center bgi-no-repeat p-10 p-sm-30" style ={{minHeight: 750}}>
            <h1 className="font-weight-boldest text-dark-75 mt-15">{error}</h1>
            <p className="font-size-h3 text-muted font-weight-normal">{error === "401" ? "Tài khoản không có quyền truy cập." : "Phiên đăng nhập đã hết hạn, vui lòng đăng nhập lại."}</p>
        </Background>
    )
}

export default NotFound
