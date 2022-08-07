import classnames from 'classnames';
import nav from './nav'

const AppAside = props => {
    const {location} = props

    const getHomePage = () =>{
        window.location.href ="/";
    }

    const menu = nav;

    return (
        <div className="aside aside-left aside-fixed d-flex flex-column flex-row-auto" id="kt_aside">
            <div className="brand flex-column-auto" id="kt_brand" kt-hidden-height="65">
                <a href="/" className="brand-logo">
                    <img alt="Logo" src="assets/media/logos/thuy_linh.png"/>
                </a>
                <button id="kt_aside_toggle" className="brand-toggle btn btn-sm px-0">
          <span className="svg-icon svg-icon svg-icon-xl">
            <svg xmlns="http://www.w3.org/2000/svg" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">
              <g stroke="none" strokeWidth="1" fill="none" fillRule="evenodd">
                <polygon points="0 0 24 0 24 24 0 24"></polygon>
                <path
                    d="M5.29288961,6.70710318 C4.90236532,6.31657888 4.90236532,5.68341391 5.29288961,5.29288961 C5.68341391,4.90236532 6.31657888,4.90236532 6.70710318,5.29288961 L12.7071032,11.2928896 C13.0856821,11.6714686 13.0989277,12.281055 12.7371505,12.675721 L7.23715054,18.675721 C6.86395813,19.08284 6.23139076,19.1103429 5.82427177,18.7371505 C5.41715278,18.3639581 5.38964985,17.7313908 5.76284226,17.3242718 L10.6158586,12.0300721 L5.29288961,6.70710318 Z"
                    fill="#000000" fillRule="nonzero"
                    transform="translate(8.999997, 11.999999) scale(-1, 1) translate(-8.999997, -11.999999)"></path>
                <path
                    d="M10.7071009,15.7071068 C10.3165766,16.0976311 9.68341162,16.0976311 9.29288733,15.7071068 C8.90236304,15.3165825 8.90236304,14.6834175 9.29288733,14.2928932 L15.2928873,8.29289322 C15.6714663,7.91431428 16.2810527,7.90106866 16.6757187,8.26284586 L22.6757187,13.7628459 C23.0828377,14.1360383 23.1103407,14.7686056 22.7371482,15.1757246 C22.3639558,15.5828436 21.7313885,15.6103465 21.3242695,15.2371541 L16.0300699,10.3841378 L10.7071009,15.7071068 Z"
                    fill="#000000" fillRule="nonzero" opacity="0.3"
                    transform="translate(15.999997, 11.999999) scale(-1, 1) rotate(-270.000000) translate(-15.999997, -11.999999)"></path>
              </g>
            </svg>
          </span>
                </button>
            </div>
            <div className="aside-menu-wrapper flex-column-fluid" id="kt_aside_menu_wrapper">
                <div id="kt_aside_menu" className="aside-menu my-4" data-menu-vertical="1" data-menu-scroll="1"
                     data-menu-dropdown-timeout="500">

                    {/*change menu*/}

                    <ul className="menu-nav">
                        <li className={classnames("menu-item menu-item-submenu", {"menu-item-here menu-item-open": true})} data-menu-toggle="hover" onClick={getHomePage}>
                            <p className="menu-link menu-toggle">
                                <span className="svg-icon menu-icon">
                                   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-house-door" viewBox="0 0 16 16">
                                        <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"/>
                                    </svg>
                                </span>
                                <span className="menu-text">Trang chủ</span>
                            </p>
                        </li>
                        {menu.map((m, idx) => m && (
                            <li key={idx}
                                className={classnames("menu-item menu-item-submenu", {"menu-item-here menu-item-open": m.children.map(m => m.link).indexOf(location.pathname) > -1})}
                                data-menu-toggle="hover">
                                <p className="menu-link menu-toggle">
                                <span className="svg-icon menu-icon">
                                  {m.logo}
                                </span>
                                    <span className="menu-text">{m.name}</span>
                                    <i className="menu-arrow"></i>
                                </p>
                                <div className="menu-submenu">
                                    <ul className="menu-subnav">
                                        {m.children.map((c, _idx) =>
                                            c.unallow ?
                                                (
                                                    <li key={_idx}
                                                        className={classnames("menu-item", {"menu-item-active": location.pathname === c.link})}>
                                                        <a href="javacripts:;"
                                                           className="menu-link">
                                                            <i className="menu-bullet menu-bullet-dot">
                                                                <span></span>
                                                            </i>
                                                            <span className="menu-text">{c.name}</span>
                                                        </a>
                                                    </li>
                                                )
                                                : (
                                                    <li key={_idx}
                                                        className={classnames("menu-item", {"menu-item-active": location.pathname === c.link})}>
                                                        <a href={c.link} className="menu-link">
                                                            <i className="menu-bullet menu-bullet-dot">
                                                                <span></span>
                                                            </i>
                                                            <span className="menu-text">{c.name}</span>
                                                        </a>
                                                    </li>
                                                ))}
                                    </ul>
                                </div>
                            </li>
                        ))}
                    </ul>
                    {/*end old menu*/}
                </div>
            </div>
        </div>
    )
}

export default AppAside


