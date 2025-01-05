import { updatePassword } from "@/api/userApi";
import { Button } from "@/shared/components/Button";
import { Input } from "@/shared/components/Input";
import { useMemo, useState } from "react";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import { toast } from "react-toastify";

export const NewPassword = () => {
  const [password, setPassword] = useState("");
  const [passwordRepeat, setPasswordRepeat] = useState("");
  const [apiProgress, setApiProgress] = useState(false);
  const [errorMessage, setErrorMessage] = useState({});
  const { token } = useParams();
  const { t } = useTranslation();

  const passwordRepeatError = useMemo(() => {
    if (password !== passwordRepeat) {
      return t("passwordMismatch");
    }
    return undefined;
  }, [password, passwordRepeat, t]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessage({});
    setApiProgress(true);
    try {
      const response = await updatePassword(token, password);
      toast.success(response.data.message);
    } catch (error) {
      if (error.response.data?.data) {
        if (error.response.data.status === 400) {
          setErrorMessage(error.response.data.validationError);
        } else {
          toast.error(error.response.data.message);
        }
      } else {
        toast.error(error.response.data.message);
      }
    } finally {
      setApiProgress(false);
    }
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
          <div className="card p-3">
            <form onSubmit={handleSubmit}>
              <div className="card-header mb-3">
                <h3>{t("updatePassword")}</h3>
              </div>
              <Input
                id={"password"}
                type={"password"}
                label={t("password")}
                error={errorMessage.password}
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
              />
              <Input
                id={"passwordRepeat"}
                type={"password"}
                label={t("passwordRepeat")}
                error={passwordRepeatError}
                onChange={(e) => {
                  setPasswordRepeat(e.target.value);
                }}
              />
              <Button
                children={t("update")}
                disabled={!password || password !== passwordRepeat}
                apiProgress={apiProgress}
                btnClass={"btn btn-primary w-100"}
              />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};
